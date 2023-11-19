package com.finalproject.frameworks.UILogic.controllers;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    private static Navigation instance = new Navigation();

    public static Navigation getInstance() {
        return instance;
    }

    private Navigation() {}

    public void fireEvent(Stage currentStage, EventType<Event> root, SceneChangeEvent sceneChangeEvent) {
        Event.fireEvent(currentStage, sceneChangeEvent);
    }

    public static class SceneChangeEvent extends Event {
        public static final EventType<SceneChangeEvent> SCENE_CHANGE_EVENT = new EventType<>("SCENE_CHANGE");

        private Scene newScene;

        public SceneChangeEvent(Scene newScene) {
            super(SCENE_CHANGE_EVENT);
            this.newScene = newScene;
        }

        public Scene getNewScene() {
            return newScene;
        }
    }

    public void registerSceneChangeEventHandler(Node source, EventHandler<SceneChangeEvent> handler) {
        source.addEventHandler(SceneChangeEvent.SCENE_CHANGE_EVENT, handler);
    }

    public void fireEventSceneChange(Node source, Scene newScene) {
        SceneChangeEvent sceneChangeEvent = new SceneChangeEvent(newScene);
        Event.fireEvent(source, sceneChangeEvent);
    }
}
