scenarios="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/data/scenarios"
networkmod_jar="/Users/austinmichne/Research/ChesterIcarus/NetworkModification/target/NetworkModification-jar-with-dependencies.jar"
simulation_jar="/Users/austinmichne/Research/ChesterIcarus/Simulator/target/Icarus-jar-with-dependencies.jar"
networkmod_jar_args="-Djava.awt.headless=true -jar"
simulation_jar_args="-Djava.awt.headless=true -jar"
python handler.py \
    "--path" "${scenarios}" \
    "--networkmod-jar" "${networkmod_jar}" \
    "--networkmod-jar-args" "${networkmod_jar_args}" \
    "--preserve-configs" \
    "--convert-events" \
    "--shell"
    # "--simulation-jar" "${simulation_jar}" \
    # "--simulation-jar-args" "${simulation_jar_args}" \
