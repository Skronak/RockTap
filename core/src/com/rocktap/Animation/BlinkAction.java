package com.rocktap.Animation;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BlinkAction extends SequenceAction {

    public BlinkAction(float duration) {
        super(Actions.fadeOut(duration/2),Actions.fadeIn(duration));
    }
}
