public class SocialMediaSystem {
    public static void main(String[] args) {
        SocialMediaSystem social = new SocialMediaSystem();
        social.addUser("harman", "Harman", 26);
        social.addUser("anish", "Anish", 28);
        social.addUser("nitish", "Nitish", 30);
        social.addUser("rohit", "Rohit", 32);

        social.addFriend("harman", "rohit");
        social.addFriend("harman", "nitish");
        social.addFriend("harman", "anish");

        social.displayFriends("harman");
        social.countFriends("harman");

        social.findMutualFriends("harman", "anish");

        social.removeFriend("harman", "nitish");
        social.displayFriends("harman");
    }
    class User {
        String userId;
        String name;
        int age;
        FriendNode friends;
        User next;

        public User(String userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friends = null;
            this.next = null;
        }
    }

    class FriendNode {
        String friendId;
        FriendNode next;

        public FriendNode(String friendId) {
            this.friendId = friendId;
            this.next = null;
        }
    }

    User head = null;

    public void addUser(String userId, String name, int age) {
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
    }

    public User findUser(String userId) {
        User current = head;
        while (current != null) {
            if (current.userId.equals(userId)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void addFriend(String userId1, String userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("Users not found.");
            return;
        }
        FriendNode friend1 = new FriendNode(userId2);
        friend1.next = user1.friends;
        user1.friends = friend1;

        FriendNode friend2 = new FriendNode(userId1);
        friend2.next = user2.friends;
        user2.friends = friend2;
    }

    public void removeFriend(String userId1, String userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("Users not found.");
            return;
        }
        user1.friends = removeFriendFromList(user1.friends, userId2);
        user2.friends = removeFriendFromList(user2.friends, userId1);
    }

    private FriendNode removeFriendFromList(FriendNode head, String friendId) {
        if (head == null) return null;
        if (head.friendId.equals(friendId)) return head.next;
        FriendNode current = head;
        while (current.next != null && !current.next.friendId.equals(friendId)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
        return head;
    }

    public void displayFriends(String userId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Friends of " + user.name + ": ");
        FriendNode current = user.friends;
        while (current != null) {
            System.out.print(current.friendId + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void findMutualFriends(String userId1, String userId2) {
        User user1 = findUser(userId1);
        User user2 = findUser(userId2);
        if (user1 == null || user2 == null) {
            System.out.println("Users not found.");
            return;
        }
        System.out.print("Mutual friends: ");
        FriendNode f1 = user1.friends;
        while (f1 != null) {
            FriendNode f2 = user2.friends;
            while (f2 != null) {
                if (f1.friendId.equals(f2.friendId)) {
                    System.out.print(f1.friendId + " ");
                }
                f2 = f2.next;
            }
            f1 = f1.next;
        }
        System.out.println();
    }

    public void searchUser(String key) {
        User current = head;
        while (current != null) {
            if (current.userId.equals(key) || current.name.equalsIgnoreCase(key)) {
                System.out.println("User Found: " + current.name + ", ID: " + current.userId + ", Age: " + current.age);
                return;
            }
            current = current.next;
        }
        System.out.println("User not found.");
    }

    public void countFriends(String userId) {
        User user = findUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        int count = 0;
        FriendNode current = user.friends;
        while (current != null) {
            count++;
            current = current.next;
        }
        System.out.println("" + user.name + " has " + count + " friends.");
    }
}

//ouput
// Friends of Harman: anish nitish rohit
//Harman has 3 friends.
//Mutual friends:
//Friends of Harman: anish rohit