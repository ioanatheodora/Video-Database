package commands;

import actor.Actor;
import actor.ActorsAwards;
import common.Constants;
import database.Database;
import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public final class ActorQuery {
    private ArrayList<Actor> actors;

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public ActorQuery(final ArrayList<Actor> actors) {
        this.actors = actors;
    }

    private void sortActorsName(final ArrayList<Actor> actorsUnsorted, final String criteria) {
        if (criteria.equals(Constants.ASC)) {
            actorsUnsorted.sort(new Comparator<Actor>() {
                @Override
                public int compare(final Actor o1, final Actor o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        } else {
            actorsUnsorted.sort(new Comparator<Actor>() {
                @Override
                public int compare(final Actor o1, final Actor o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
        }
    }

    private String descriptionQuery(final ActionInputData actionInputData,
                                    final Database database) {
        ArrayList<Actor> filteredActors = new ArrayList<>();
        boolean ok;

        for (Actor actor : database.getActors()) {
            String[] careerDescriptionTemp =
                    actor.getCareerDescription().toLowerCase().split("[^a-zA-Z]+");
            ArrayList<String> careerDescription =
                    new ArrayList<>(Arrays.asList(careerDescriptionTemp));

            ok = true;
            if (actionInputData.getFilters().get(2) != null) {
                for (String description : actionInputData.getFilters().get(2)) {
                    // 2 --> words
                    if (!careerDescription.contains(description)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    filteredActors.add(actor);
                }
            }
        }

        sortActorsName(filteredActors, actionInputData.getSortType());
        return toString(filteredActors);
    }

    private String awardsQuery(final ActionInputData actionInputData, final Database database) {
        ArrayList<Actor> awardedActors = new ArrayList<>();

        for (Actor actor : actors) {
            if (actionInputData.getFilters().get(3) != null) {
                boolean ok = true;
                for (String award : actionInputData.getFilters().get(3)) {
                    // 4 --> awards
                    if (!(actor.getAwards().containsKey(ActorsAwards.valueOf(award)))) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    awardedActors.add(actor);
                }
            }
        }

        sortActorsName(awardedActors, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.ASC)) {
            awardedActors.sort(new Comparator<Actor>() {
                @Override
                public int compare(final Actor o1, final Actor o2) {
                    return o1.getNoAwards() - o2.getNoAwards();
                }
            });
        } else {
            awardedActors.sort(new Comparator<Actor>() {
                @Override
                public int compare(final Actor o1, final Actor o2) {
                    return o2.getNoAwards() - o1.getNoAwards();
                }
            });
        }

        return toString(awardedActors);
    }

    private String averageQuery(final ActionInputData actionInputData, final Database database) {
        int N = actionInputData.getNumber();
        ArrayList<Actor> averageActors = new ArrayList<>();
        for (Actor actor : database.getActors()) {
            actor.getAverageGradeActor(database);
            if (actor.getAverageGrade() > 0) {
                averageActors.add(actor);
            }
        }
        sortActorsName(averageActors, actionInputData.getSortType());

        if (actionInputData.getSortType().equals(Constants.DESC)) {
            averageActors.sort(Comparator.comparingDouble(Actor::getAverageGrade).reversed());
        } else {
            averageActors.sort(Comparator.comparingDouble(Actor::getAverageGrade));
        }
        for (int i = averageActors.size(); i > N; i--) {
            averageActors.remove(i - 1);
        }

        return toString(averageActors);
    }

    private String toString(final ArrayList<Actor> awardedActors) {
        String array = "[";
        for (int i = 0; i < awardedActors.size(); i++) {
            if (i != awardedActors.size() - 1) {
                array = array + awardedActors.get(i).getName() + ", ";
            } else {
                array = array + awardedActors.get(i).getName();
            }
        }
        array = array + "]";
        return array;
    }

    public String doTheQuery(final ActionInputData actionInputData, final Database database) {
        String message = "Query result: ";
        if (actionInputData.getCriteria().equals(Constants.AWARDS)) {
            message += awardsQuery(actionInputData, database);
        }
        if (actionInputData.getCriteria().equals((Constants.FILTER_DESCRIPTIONS))) {
            message += descriptionQuery(actionInputData, database);
        }
        if (actionInputData.getCriteria().equals(Constants.AVERAGE)) {
            message += averageQuery(actionInputData, database);
        }

        return message;
    }
}
