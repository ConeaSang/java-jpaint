package application.observers;

public interface ISubject {
    void registerObserver(IObserver _observer);
    void removeObserver(IObserver _observer);
}
