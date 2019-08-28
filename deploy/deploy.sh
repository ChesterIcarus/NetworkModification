#!/bin/bash




data="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/deploy/data.zip"
data_dir="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/deploy/data"

simulator_dir="/Users/austinmichne/Research/ChesterIcarus/Simulator"
simulator="${simulator_dir}/target/Icarus-jar-with-dependencies.jar"

netmod_dir="/Users/austinmichne/Research/ChesterIcarus/NetworkModification"
netmod="${netmod_dir}/target/NetworkModification-jar-with-dependencies.jar"

wrapper_dir="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/wrapper"
wrapper="wrapper.zip"

dependencies="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/deploy/setup_dependencies.sh"
python="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/deploy/setup_python.sh"
scenarios="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/deploy/setup_scenarios.sh"

echo "Enter the address of a currently running AWS instance which you'd like to run an"
echo "instance of the Icarus simulation, followed by [ENTER]:"

read -r aws_url

if [ -z "${AWS_SSH_AUTH_KEY}" ]; then
    echo "Must set environment variable AWS_SSH_AUTH_KEY with RSA valid key for given instance"
    exit
fi

# Recompiling both jars to most recent form
#currdir=$PWD
#cd $simulator_dir
#mvn clean compile assembly:single
#cd $netmod_dir
#mvn clean compile assembly:single
#cd $currdir

#if [ -f $wrapper ]; then
#  rm $wrapper
#fi
# zip -r "${wrapper##*/}" $wrapper_dir

# shellcheck disable=SC2206
# These files should do everything we need for a simulation
# They are uploaded to the base directory of the EC2 instance
upload_files=( $data $dependencies $python $wrapper $scenarios $simulator $netmod )
aws_user="ec2-user"
for file in "${upload_files[@]}"; do
    echo "${file}"
    if [ -f "$file" ]; then
        scp -o "StrictHostKeyChecking=no" -i $AWS_SSH_AUTH_KEY "$file" \
            "${aws_user}@${aws_url}:/home/${aws_user}/${file##*/}"
    else
        exit 1
    fi
done


ssh -tt "-o StrictHostKeyChecking no" \
    -i $AWS_SSH_AUTH_KEY "${aws_user}@${aws_url}" /bin/bash << EOF
sudo chmod +rwx *
sudo rm -r data
sudo rm -r wrapper
unzip data.zip
unzip wrapper.zip
sudo /bin/bash ${dependencies##*/}
sudo /bin/bash ${python##*/}
sudo /bin/bash ${scenarios##*/}
EOF
