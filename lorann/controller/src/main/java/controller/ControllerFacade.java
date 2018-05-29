package controller;

import java.awt.event.KeyEvent;
import java.io.IOException;

import model.IModel;
import view.IView;

/**
 * <h1>The Class ControllerFacade provides a facade of the Controller component.</h1>
 *
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
public class ControllerFacade implements IController, IOrderPerformer {

    /** The view. */
    private final IView  view;

    /** The model. */
    private final IModel model;
    
    /** The order. */
    private KeyEvent stackOrder;
    
    /** The speed of refresh. */
    private static int speed = 300;

    /**
     * Instantiates a new controller facade.
     *
     * @param view
     *            the view
     * @param model
     *            the model
     */
    public ControllerFacade(final IView view, final IModel model) {
        super();
        this.view = view;
        this.model = model;
    }

    /**
     * Start.
     *
     * @throws SQLException
     *             the SQL exception
     */
    public void start() throws InterruptedException {
        while(this.getModel().getMyCharacter().isAlive()) {
        	Thread.sleep(speed);
        	switch(this.getStackOrder().getKeyCode()) {
        		case KeyEvent.VK_RIGHT:
        			this.getModel().getMyCharacter().moveRight();
        			break;
        		case KeyEvent.VK_LEFT:
        			this.getModel().getMyCharacter().moveLeft();
        			break;
        		case KeyEvent.VK_UP:
        			this.getModel().getMyCharacter().moveUp();
        			break;
        		case KeyEvent.VK_DOWN:
        			this.getModel().getMyCharacter().moveDown();
        			break;
        		default:
        			this.getModel().getMyCharacter().doNothing();
        			break;
        	}
        	this.stackOrder = null;
        }
        this.getView().displayMessage("You're dead");
    }

    /**
     * Gets the view.
     *
     * @return the view
     */
    public IView getView() {
        return this.view;
    }

    /**
     * Gets the model.
     *
     * @return the model
     */
    public IModel getModel() {
        return this.model;
    }
    
    /**
     * Stock the order.
     *
     * @return the model
     */
    public void performOrder(KeyEvent userOrder) {
    	this.setStackOrder(userOrder);
    }

    /**
     * Gets the order.
     *
     * @return the model
     */
	public KeyEvent getStackOrder() {
		return stackOrder;
	}

	/**
     * Set the order.
     *
     * @return the model
     */
	public void setStackOrder(KeyEvent stackOrder) {
		this.stackOrder = stackOrder;
	}
	
	/**
     * Gets the Order performer.
     *
     * @return the model
     */
	public IOrderPerformer getOrderPerformer() {
		return this;
	}
}
