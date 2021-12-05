package commands;

import common.Constants;
import database.Database;
import fileio.ActionInputData;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;

public final class UserQuery {
    private String toString(final ArrayList<User> users) {
        StringBuilder array = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            if (i != users.size() - 1) {
                array.append(users.get(i).getUsername()).append(", ");
            } else {
                array.append(users.get(i).getUsername());
            }
        }
        array.append("]");
        return array.toString();
    }

    private void sortUsersName(final ArrayList<User> users, final String criteria) {
        if (criteria.equals(Constants.ASC)) {
            users.sort(Comparator.comparing(User::getUsername));
        } else {
            users.sort((o1, o2) -> o2.getUsername().compareTo(o1.getUsername()));
        }
    }

    /**
     * Does the User Query
     * @param actionInputData input data of the action
     * @param database contains users, actors, movies and shows
     * @return a String - message following the state of the query
     */
    public String doTheQuery(final ActionInputData actionInputData, final Database database) {
        ArrayList<User> active = new ArrayList<>();
        int number  = actionInputData.getNumber();

       for (User user : database.getUsers()) {
           if (user.getRatingsGiven() != 0) {
               active.add(user);
           }
       }

       sortUsersName(active, actionInputData.getSortType());

       if (actionInputData.getSortType().equals(Constants.DESC)) {
                active.sort((o1, o2) -> o2.getRatingsGiven() - o1.getRatingsGiven());
            } else {
                active.sort(Comparator.comparingInt(User::getRatingsGiven));
            }
        if (active.size() > number) {
            active.subList(number, active.size()).clear();
        }
            return "Query result: " + toString(active);
        }
    }

