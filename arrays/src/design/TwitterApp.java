package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TwitterApp {

	public static void main(String[] args) {
		Twitter twitter = new Twitter();

		twitter.postTweet(1, 5);

		twitter.getNewsFeed(1);

		twitter.follow(1, 2);

		twitter.postTweet(2, 6);

		twitter.getNewsFeed(1);

		twitter.unfollow(1, 2);

		twitter.getNewsFeed(1);
	}

}

class Twitter {

	Map<Integer, User> users;
	private static int timestamp = 0;

	/** Initialize your data structure here. */
	public Twitter() {
		users = new HashMap<>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		User user = users.get(userId);
		if (user == null) {
			user = new User(userId);
		}
		user.post(tweetId, timestamp);
		users.put(userId, user);
		timestamp++;
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
	 * the news feed must be posted by users who the user followed or by the user
	 * herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		User user = users.get(userId);
		List<Integer> list = new ArrayList<>();
		if (user == null) {
			return list;
		}
		// System.out.println(users.size());
		PriorityQueue<Tweet> queue = new PriorityQueue<>((tweet1, tweet2) -> {
			return tweet2.timestamp - tweet1.timestamp;
		});
		for (Integer follower : user.followers) {
			// System.out.println(follower);
			if (users.get(follower) != null && users.get(follower).tweet_head != null)
				queue.add(users.get(follower).tweet_head);
		}
		int i = 0;
		while (!queue.isEmpty() && i < 10) {
			Tweet tweet = queue.poll();
			if (tweet.next != null) {
				queue.add(tweet.next);
			}
			list.add(tweet.id);
			i++;
		}
		return list;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (!users.containsKey(followerId)) {
			User user = new User(followerId);
			users.put(followerId, user);
		}
		if (!users.containsKey(followeeId)) {
			User user = new User(followeeId);
			users.put(followeeId, user);
		}
		users.get(followerId).follow(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (!users.containsKey(followerId) || followerId == followeeId) {
			return;
		}
		User user = users.get(followerId);
		user.unFollow(followeeId);
	}
}

class Tweet {
	public int id;
	public Tweet next;
	public int timestamp;
}

class User {
	int id;
	Set<Integer> followers;
	Tweet tweet_head;

	public User(int id) {
		this.id = id;
		followers = new HashSet<>();
		follow(id);
	}

	public void follow(int followee) {
		followers.add(followee);
	}

	public void unFollow(int followee) {
		followers.remove(followee);
	}

	public void post(int tweetId, int timestamp) {
		if (tweet_head == null) {
			tweet_head = new Tweet();
			tweet_head.id = tweetId;
			tweet_head.timestamp = timestamp;
			return;
		}
		Tweet head = new Tweet();
		head.id = tweetId;
		head.timestamp = timestamp;
		head.next = tweet_head;
		tweet_head = head;
	}
}