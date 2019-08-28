scenarios="data/scenarios"
plan="SWUS_Plans_FINAL.xml"
network="SWUSFULLFINAL_NET.xml"
networkmod_jar="NetworkModification-jar-with-dependencies.jar"
simulation_jar="Icarus-jar-with-dependencies.jar"
networkmod_jar_args="-Djava.awt.headless=true -jar"
simulation_jar_args="-Djava.awt.headless=true -jar"

python3.6 wrapper/handler.py \
    "--path" "${scenarios}" \
    "--plan" "${plan}" \
    "--network" "${network}" \
    "--networkmod-jar" "${networkmod_jar}" \
    "--networkmod-jar-args" "${networkmod_jar_args}" \
    "--simulation-jar" "${simulation_jar}" \
    "--simulation-jar-args" "${simulation_jar_args}" \
    "--preserve-configs" \
    "--convert-events" \
    "--java" "java8" \
    "--shell"