package InMemoryMatsim;

import org.matsim.core.config.Config;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;

import java.util.Map;

public class ConfigScoringUtils {

    private ConfigScoringUtils(){}

    public static void removeActivityScoring(Config config){
        for (PlanCalcScoreConfigGroup.ActivityParams params : config.planCalcScore().getActivityParams()){
            params.setTypicalDuration(1.0);
            params.setEarliestEndTime(1.0);
            params.setLatestStartTime(86400.0);
            params.setClosingTime(86400.0);
            params.setOpeningTime(0.0);
            params.setScoringThisActivityAtAll(false);
        }
    }

    public static void addMode(Config config, String mode){
        config.planCalcScore().addModeParams(new PlanCalcScoreConfigGroup.ModeParams(mode));
    }

    public static void addActivityType(Config config, String type){
        PlanCalcScoreConfigGroup.ActivityParams param = new PlanCalcScoreConfigGroup.ActivityParams();
        param.setActivityType(type);
        config.planCalcScore().addActivityParams(param);
    }

    public static void setModeAttr(Config config, String mode, String attr, String value){
        Map<String, PlanCalcScoreConfigGroup.ModeParams> modes = config.planCalcScore().getModes();
        PlanCalcScoreConfigGroup.ModeParams param = modes.get(mode);
        if (param == null){
            System.out.println("No mode " + mode + " in the given config. Exiting");
            System.exit(1);
        }

        String[] validAttrs = {"Constant", "DailyMonetaryConstant", "DailyUtilityConstant",
                "MarginalUtilityOfDistance", "MarginalUtilityOfTraveling", "MonetaryDistanceRate"};
        // TODO: Call the proper attribute to be modified based on the attr string
    }


}
