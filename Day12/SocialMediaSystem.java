
import java.util.ArrayList;
import java.util.List;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }

    void addFriend(int friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    void removeFriend(int friendId) {
        friendIds.remove(Integer.valueOf(friendId));
    }

    int getFriendCount() {
        return friendIds.size();
    }
}

class SocialMedia {
    private User head;

    // Add a new user
    public void addUser(int id, String name, int age) {
        User newUser = new User(id, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }
        System.out.println("User added: " + name);
    }

    private User getUserById(int id) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriendConnection(int id1, int id2) {
        if (id1 == id2) {
            System.out.println("Cannot add self as friend.");
            return;
        }
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 != null && u2 != null) {
            u1.addFriend(id2);
            u2.addFriend(id1);
            System.out.println("Friend connection added between " + u1.name + " and " + u2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Remove a friend connection
    public void removeFriendConnection(int id1, int id2) {
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 != null && u2 != null) {
            u1.removeFriend(id2);
            u2.removeFriend(id1);
            System.out.println("Friend connection removed between " + u1.name + " and " + u2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Display all friends of a specific user
    public void displayFriends(int id) {
        User u = getUserById(id);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Friends of " + u.name + ":");
        if (u.friendIds.isEmpty()) {
            System.out.println("No friends found.");
        } else {
            for (int fid : u.friendIds) {
                User friend = getUserById(fid);
                if (friend != null) {
                    System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
                }
            }
        }
    }

    // Search for a user by name
    public void searchUserByName(String name) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User found: " + temp.name + " (ID: " + temp.userId + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No user with name " + name + " found.");
    }

    // Search for a user by ID
    public void searchUserById(int id) {
        User user = getUserById(id);
        if (user != null)
            System.out.println("User found: " + user.name + " (ID: " + user.userId + ")");
        else
            System.out.println("User not found.");
    }

    // Count the number of friends for each user
    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.getFriendCount() + " friend(s).");
            temp = temp.next;
        }
    }

    // Find mutual friends between two users
    public void findMutualFriends(int id1, int id2) {
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        System.out.println("Mutual friends between " + u1.name + " and " + u2.name + ":");
        boolean hasMutual = false;
        for (int fid : u1.friendIds) {
            if (u2.friendIds.contains(fid)) {
                User mutual = getUserById(fid);
                if (mutual != null) {
                    System.out.println("- " + mutual.name + " (ID: " + mutual.userId + ")");
                    hasMutual = true;
                }
            }
        }
        if (!hasMutual) {
            System.out.println("No mutual friends.");
        }
    }
}

public class SocialMediaSystem {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        // Adding users
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 27);
        sm.addUser(3, "Charlie", 24);
        sm.addUser(4, "Diana", 26);

        // Adding friend connections
        sm.addFriendConnection(1, 2);
        sm.addFriendConnection(1, 3);
        sm.addFriendConnection(2, 4);

        System.out.println();

        // Displaying friends
        sm.displayFriends(1);
        sm.displayFriends(2);

        System.out.println();

        // Mutual friends
        sm.findMutualFriends(1, 2);

        System.out.println();

        // Search
        sm.searchUserById(3);
        sm.searchUserByName("Diana");

        System.out.println();

        // Count friends
        sm.countFriends();

        System.out.println("\nRemoving friend connection between Alice and Bob...");
        sm.removeFriendConnection(1, 2);
        sm.displayFriends(1);
    }
}




