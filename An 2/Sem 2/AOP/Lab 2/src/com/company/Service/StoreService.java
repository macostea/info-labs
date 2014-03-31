package com.company.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import com.company.Model.Student;
import com.company.Repository.Repository;

public class StoreService extends Observable {
	private static Logger logger = Logger.getLogger("Students");
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private Repository<Student> repo;
	
	public StoreService(Repository<Student> repo) {
		this.repo = repo;
	}
	
	public void addElement(Student element) {
		this.repo.addElement(element);
		this.notifyObservers(this.repo.allElements());
	}
	
	public Map<Integer, Student> allElements() {
		return this.repo.allElements();
	}
	
	public int numberOfElements() {
		return this.repo.numberOfElements();
	}
	
    ////
    // Observable
    ////


    public void setObservers(ArrayList<Observer> observers) {
        logger.info("[Entering:]Controller.setObservers");
        this.observers = observers;
    }

    public ArrayList<Observer> getObservers() {
        logger.info("[Entering:]Controller.getObservers");
        return this.observers;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        logger.info("[Entering:]Controller.addObserver");
        observers.add(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        logger.info("[Entering:]Controller.deleteObserver");
        observers.remove(o);
    }

    public void notifyObservers(Observable observable, Map<Integer, Student> map) {
        logger.info("[Entering:]Controller.notifyObservers");
        for (Observer observer : this.observers) {
            observer.update(observable, map);
        }
    }
}
