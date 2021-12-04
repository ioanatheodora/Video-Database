package commands;

import common.Constants;
import database.Database;
import fileio.ActionInputData;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;

public final class UserQuery {
    private String toString(final ArrayList<User> users) {
        String array = "[";
        for (int i = 0; i < users.size(); i++) {
            if (i != users.size() - 1) {
                array = array + users.get(i).getUsername() + ", ";
            } else {
                array = array + users.get(i).getUsername();
            }
        }
        array = array + "]";
        return array;
    }

    private void sortUsersName(final ArrayList<User> users, final String criteria) {
        if (criteria.equals(Constants.ASC)) {
            users.sort(new Comparator<User>() {
                @Override
                public int compare(final User o1, final User o2) {
                    return o1.getUsername().compareTo(o2.getUsername());
                }
            });
        } else {
            users.sort(new Comparator<User>() {
                @Override
                public int compare(final User o1, final User o2) {
                    return o2.getUsername().compareTo(o1.getUsername());
                }
            });
        }
    }

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
                active.sort(new Comparator<User>() {
                    @Override
                    public int compare(final User o1, final User o2) {
                        return o2.getRatingsGiven() - o1.getRatingsGiven();
                    }
                });
            } else {
                active.sort(new Comparator<User>() {
                    @Override
                    public int compare(final User o1, final User o2) {
                        return o1.getRatingsGiven() - o2.getRatingsGiven();
                    }
                });
            }
            for (int i = active.size() - 1; i >= number; i--) {
                active.remove(i);
            }
            return "Query result: " + toString(active);
        }
    }

