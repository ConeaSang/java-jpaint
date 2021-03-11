package application.observers;

public interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);

    void notifyObservers();
}
