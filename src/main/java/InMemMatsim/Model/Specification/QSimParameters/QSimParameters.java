package InMemMatsim.Model.Specification.QSimParameters;

import InMemMatsim.Model.Specification.Core.Parameters;
import org.matsim.core.config.groups.QSimConfigGroup;
import org.matsim.core.utils.misc.Time;

import java.util.Arrays;
import java.util.Collection;

public class QSimParameters extends Parameters {
    public double startTime = 0.0D;
    public double endTime = 0.0D;
    public double timeStepSize = 1.0D;
    public double snapshotPeriod = 0.0D;
    public double flowCapFactor = 1.0D;
    public double storageCapFactor = 1.0D;
    public double stuckTime = 10.0D;

    public boolean removeStuckVehicles = false;
    public boolean usePersonIdForMissingVehicleId = true;
    public int numberOfThreads = 1;

    public double nodeOffset = 0.0D;
    public float linkWidth = 30.0F;
    public boolean insertingWaitingVehiclesBeforeDrivingVehicles = true;
    public boolean usingThreadpool = true;
    public boolean useLanes = false;
    public boolean usingFastCapacityUpdate = true;
    public boolean isSeepModeStorageFree = false;

    public Collection<String> mainModes = Arrays.asList("car");
    public Collection<String> seepModes = Arrays.asList("bike");
    public QSimConfigGroup.StarttimeInterpretation simStarttimeInterpretation = QSimConfigGroup.StarttimeInterpretation.onlyUseStarttime;
    public QSimConfigGroup.EndtimeInterpretation simEndtimeInterpretation = QSimConfigGroup.EndtimeInterpretation.onlyUseEndtime;
    public QSimConfigGroup.TrafficDynamics trafficDynamics = QSimConfigGroup.TrafficDynamics.queue;
    public QSimConfigGroup.VehicleBehavior vehicleBehavior = QSimConfigGroup.VehicleBehavior.teleport;
    public QSimConfigGroup.VehiclesSource vehiclesSource = QSimConfigGroup.VehiclesSource.defaultVehicle;
    public QSimConfigGroup.SnapshotStyle snapshotStyle = QSimConfigGroup.SnapshotStyle.equiDist;
    public QSimConfigGroup.LinkDynamics linkDynamics = QSimConfigGroup.LinkDynamics.FIFO;

    QSimParameters(){
        super();
        // TODO: Finish this. Standard parsing for primitives, fall back on MATsim *.valueOf funcs for QSimConfigGroup.*
    }
}
