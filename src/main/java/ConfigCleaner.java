import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
import org.matsim.core.controler.OutputDirectoryHierarchy;

public class ConfigCleaner {
    private ConfigCleaner(){}

    public static void clean(Config config){
        ConfigCleaner.cleanActivityTypes(config);
        ConfigCleaner.setOverrideFiles(config);
    }

    private static void cleanActivityTypes(Config config){
        for (PlanCalcScoreConfigGroup.ActivityParams params : config.planCalcScore().getActivityParams()){
            if (!params.getActivityType().equalsIgnoreCase("w") &&
                    !params.getActivityType().equalsIgnoreCase("h")){
                params.setTypicalDuration(1.0);
                params.setEarliestEndTime(1.0);
                params.setLatestStartTime(400.0);
                params.setClosingTime(1000.0);
                params.setOpeningTime(0.0);
            }
        }
    }

    private static void setOverrideFiles(Config config){
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
    }
}
