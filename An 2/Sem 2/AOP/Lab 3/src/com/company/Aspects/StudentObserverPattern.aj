package com.company.Aspects;

import com.company.Controller.Controller;
import com.company.Service.StoreService;
import com.company.Service.ChangesSubject;
import com.company.UI.GUI;

import java.util.ArrayList;
import java.util.List;

public aspect StudentObserverPattern {
	declare parents: StoreService implements Subject;
	declare parents: GUI implements Observer;

	private List<Observer> Subject.observers = new ArrayList<Observer>();
	
	public void Subject.addObserver(Observer obs) {
		System.out.println("Adding observer");
		observers.add(obs);
	}
	
	public void Subject.removeObserver(Observer obs) {
		System.out.println("Removing observer");
		observers.remove(obs);
	}
	
	public void Subject.notifyObservers(Object o, Object o2) {
		for (Observer ob : observers) {
			ob.update(o, null);
		}
	}

//    pointcut observed(StoreService storeService):execution(* com.company.Service.StoreService.add*(..)) && this(storeService);
	pointcut observed(StoreService storeService): execution(@ChangesSubject * * (..)) && this(storeService);

    StoreService storeService;

    after(Controller controller, GUI gui): initialization(com.company.UI.GUI.new(*)) && this(gui) && args(controller) {
        controller.getStoreService().addObserver(gui);
        this.storeService=controller.getStoreService();
    }

    after(StoreService storeService1) returning: observed(storeService1) {
        System.out.println("Inside ObserverAspect: notifyObservers");
        storeService1.notifyObservers(storeService1, null);
    }

    public void GUI.update(Object arg0, Object arg1) {
        System.out.println("Inside ObserverAspect: GUI.update");
        fillTable(null);
    }

    after(GUI gui): execution(* com.company.UI.GUI.dispose()) && this(gui) {
        this.storeService.removeObserver(gui);
    }
}
