package com.game.engine.controller;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class JoystickController {
    public float x;
    public float y;
    public boolean overriden = false;
    private float EPSILON = (float)0.1;
    private Control c;

    public JoystickController(Control c) {
        this.c = c;
    }

    public void checkForInput() {
        /* Get the available controllers */
        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment().getControllers();
        if (controllers.length == 0) {
            System.out.println("Found no controllers.");
            System.exit(0);
        }

        for (int i = 0; i < controllers.length; i++) {
            /* Prevent capturing joystick input */
            if(controllers[i].getType() != Controller.Type.GAMEPAD) continue;

            /* Remember to poll each one */
            controllers[i].poll();

            /* Get the controllers event queue */
            EventQueue queue = controllers[i].getEventQueue();

            /* Create an event object for the underlying plugin to populate */
            Event event = new Event();

            /* For each object in the queue */
            while (queue.getNextEvent(event)) {
                Component comp = event.getComponent();
                float value = event.getValue();

                value = (float)Math.round(value * 100)/100;
                if(Math.abs(value) - EPSILON < 0.1) {
                    value = 0;
                }
                
                if(comp.getName().equals("Y Axis")) {
                    y = value;
                }

                if (comp.getName().equals("X Axis")) {
                    x = value;
                }
            }
        }
        if(!overriden) {
            c.x = x;
            c.y = y;
        }
    }
}

