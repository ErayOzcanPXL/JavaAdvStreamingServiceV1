package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class Profile implements Comparable<Profile> {
	private String name;
	private LocalDate dateOfBirth;
    private Queue<Content> recentlyWatched;
    private Queue<Content> currentlyWatching;
    private Set<Content> myList;
    private String avatar;

	public Profile(String name, String avatar) {
		this.name = name;
        this.avatar = avatar;
        recentlyWatched = new PriorityQueue<>();
        currentlyWatching = new PriorityQueue<>();
        myList = new HashSet<>();
	}

    public Profile(String name) {
        this(name, "profile1");
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		if (dateOfBirth.isAfter(LocalDate.now())) {
			throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
		}

		this.dateOfBirth = dateOfBirth;
	}

    public String getAvatar() {
        return this.avatar;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }

        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
    }

    public void startWatching(Content content) {
        if (!currentlyWatching.contains(content)) {
            currentlyWatching.add(content);
        }
    }

    public void finishWatching(Content content) {
        if (currentlyWatching.contains(content)) {
            currentlyWatching.remove(content);
            recentlyWatched.add(content);
        }
    }

    public Queue<Content> getRecentlyWatched() {
        return this.recentlyWatched;
    }

    public Queue<Content> getCurrentlyWatching() {
        return this.currentlyWatching;
    }

    public Set<Content> getMyList() {
        return this.myList;
    }

    public void addToMyList(Content content) {
        myList.add(content);
    }

    public boolean isInMyList(Content content) {
        return myList.contains(content);
    }

    public boolean allowedToWatch(Content content) {
        return content.getMaturityRating().getMinimumAge() <= getAge();
    }

    public void removeFromMyList(Content content) {
        myList.remove(content);
    }

    @Override
    public int compareTo(Profile profile) {
        return this.getName().compareTo(profile.getName());
    }
}
