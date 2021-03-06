package com.harium.suneidesis.linguistic.english.box;

import com.harium.suneidesis.concept.Action;
import com.harium.suneidesis.concept.Being;
import com.harium.suneidesis.chat.input.InputContext;
import com.harium.suneidesis.chat.output.Output;
import com.harium.suneidesis.concept.Concept;
import com.harium.suneidesis.concept.helper.Provenance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleActionParser extends BeingParser implements ActionParser {

    public static final String DID = "did";

    @Override
    public boolean parse(InputContext context, Output output) {
        Being being = getBeing(context);
        if (being != null) {
            output.print("Being undefined!");
        }

        String query = context.getSentence();
        String[] parts = query.split(" ");

        String querySubject = "";

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equalsIgnoreCase("about")) {
                querySubject = parts[i + 1];
                break;
            }
        }

        if (!querySubject.isEmpty()) {

            /*Collection<Fact> actions = being.getMemories().getAll();

            Fact action = null;

            if (!Character.isUpperCase(querySubject.charAt(0))) {
                action = findActionByName(querySubject, actions);
            } else {
                action = findActionByActorsName(querySubject, actions);
            }

            if (action != null) {
                output.print(describeAction(action));
            } else if (querySubject.endsWith("s")) {
                action = findActionByActorsName(querySubject.substring(0, querySubject.length() - 1), actions);
                if (action != null) {
                    output.print(describeAction(action));
                }
            }*/
        }

        output.print("No.");
        return BaseEnglishBox.checkAction(parts[0], DID);
    }

    private List<Concept> findActionByActorsName(String actorName, Collection<Action> actions) {
        List<Concept> facts = new ArrayList<>();
        String name = actorName.toLowerCase();

        for (Action concept : actions) {
            boolean hasActor = concept.getSubject() !=null && concept.getSubject().getName().toLowerCase().contains(name);
            boolean hasTarget = concept.getObject() != null && concept.getObject().getName().toLowerCase().contains(name);

            if (hasActor || hasTarget) {
                facts.add(concept);
            }
        }

        return facts;
    }

    private List<Concept> findByActionName(String actionName, Collection<Action> actions) {
        List<Concept> facts = new ArrayList<>();
        for (Action concept : actions) {
            if (concept.getPredicate() !=null && concept.getPredicate().getName().toLowerCase().contains(actionName)) {
                facts.add(concept);
            }
        }

        return facts;
    }

    public String describeAction(Action frame) {
        StringBuilder builder = new StringBuilder();

        // Change Based on source
        builder.append("I heard that");

        builder.append(" ");
        builder.append(frame.getSubject().getName());
        builder.append(" ");

        String actionAsText = frame.getPredicate().getName().toLowerCase();
        builder.append(actionAsText);
        if (!actionAsText.endsWith("s")) {
            builder.append("s");
        }

        if (frame.getObject() != null) {
            builder.append(" ");
            builder.append(frame.getObject().getName());

            if (frame.getObjectPart() != null) {

                if (!frame.getObjectPart().getName().isEmpty()) {
                    builder.append("'s ");
                    builder.append(frame.getObjectPart().getName());
                }
            }
        }

        if (frame.getPlace() != null) {
            builder.append(" ");
            builder.append(frame.getPlace().getName());
        }

        if (frame.getTime() != null) {
            builder.append(", ");
            builder.append(frame.getTime().getName());
        }

        return builder.toString();
    }

}
