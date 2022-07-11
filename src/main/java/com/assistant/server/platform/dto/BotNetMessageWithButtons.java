package com.assistant.server.platform.dto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class BotNetMessageWithButtons extends BotNetMessage {
    private boolean inlineButtons = true; // use inline buttons by default
    private List<List<BotNetButton>> buttonsMatrix = new ArrayList<>();

    BotNetMessageWithButtons(BotNetMessage message) {
        super(message.getUserChatId(), message.getMessage(), message.getUiPlatform());
    }

    /** Method to add button to the last row of buttons */
    public BotNetMessageWithButtons addButton(@NotNull final BotNetButton botNetButton) {
        buttonsMatrix.get(buttonsMatrix.size() - 1).add(botNetButton);
        return this;
    }

    /** Method to set new row of buttons */
    public BotNetMessageWithButtons setNewButtonsLine() {
        buttonsMatrix.add(new LinkedList<>());
        return this;
    }

    public void cleanButtons() {
        buttonsMatrix.clear();
        setNewButtonsLine();
    }

    public List<List<BotNetButton>> getButtonsMatrix() {
        final List<List<BotNetButton>> result = new LinkedList<>();
        for (List<BotNetButton> buttonsRow : buttonsMatrix ) {
            result.add(new LinkedList<>(buttonsRow));
        }
        return result;
    }

    public boolean hasButtons() {
        return getButtonsMatrix().size() > 1 || getButtonsMatrix().get(0).size() > 0;
    }

    public boolean isInlineButtons() {
        return inlineButtons;
    }

    public void setInlineButtons(boolean inlineButtons) {
        this.inlineButtons = inlineButtons;
    }
}
