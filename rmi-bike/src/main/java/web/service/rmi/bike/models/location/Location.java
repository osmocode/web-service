package web.service.rmi.bike.models.location;

import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.bike.models.feedback.Feedback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Location extends UnicastRemoteObject implements LocationInterface {
    private Date start;
    private Date end;
    private long tenantId;

    public Location(Date start, Date end, long tenantId) throws RemoteException {
        super();
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "start=" + start +
                ", end=" + end +
                ", tenant=" + tenantId +
                '}';
    }
}
