import java.util.*;
import java.util.regex.Pattern;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class User {
    protected String accountName;
    protected String password;
    protected List<String> transactionHistory;
    protected double rating;

    /**
     * Default constructor of a User
     */
    public User(){
        this.accountName = "accountname";
        this.password = "password";
        this.rating = 0.0;
        this.transactionHistory = new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String get(int i) {
                return null;
            }

            @Override
            public String set(int i, String s) {
                return null;
            }

            @Override
            public void add(int i, String s) {

            }

            @Override
            public String remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @Override
            public ListIterator<String> listIterator(int i) {
                return null;
            }

            @Override
            public List<String> subList(int i, int i1) {
                return null;
            }
        };
    }

    /**
     * Constructor of a User
     * @throws IllegalArgumentException if either the account name or the password are invalid (checked using static methods below)
     * @param accountNameIn the desired account name for the User
     * @param passwordIn the desired password for the User
     */
    public User(String accountNameIn, String passwordIn){
        if(!isAccountNameValid(accountNameIn)){
            throw new IllegalArgumentException("invalid account name");
        }else {
            this.accountName = accountNameIn;
        }

        if(!isPasswordValid(passwordIn)){
            throw new IllegalArgumentException("invalid password");
        }else {
            this.password = passwordIn;
        }
        this.rating = 0.0;
    }

    /**
     * Accessor for the account name property of a User
     * @return the account name of the User
     */
    public String getAccountName(){
        return this.accountName;
    }

    /**
     * Accessor for the password property of a User
     * @return the password of the User
     */
    public String getPassword(){
        return this.password;
    }
    public List <String> getTransactionHistory(){return this.transactionHistory;}
    public double getRating(){return this.rating;}

    /**
     * Accessor for the rating property of a User
     * @return the rating of the User
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * Checks to see if a desired account name has any illegal characters (spaces)
     * @param accountName the account name to check
     * @return true if a valid account name, false if an invalid account name
     */
    public static boolean isAccountNameValid(String accountName) {
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, accountName);
    }

    /**
     * Checks to see if a desired password has any illegal characters (spaces)
     * @param password the password to check
     * @return true if a valid password, false if an invalid password
     */
    public static boolean isPasswordValid(String password) {
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, password);
    }

    /**
     * Checks to see if a desired rating is valid (between 0 and 5 inclusive, no more than 2 decimal places)
     * @param rating the rating to check
     * @return true if a valid rating, false if an invalid rating
     */
    public static boolean isRatingValid(double rating) {
        String ratingStr = "" + rating;
        if(rating >= 0 && rating <= 5) {
            if (ratingStr.length() - ratingStr.lastIndexOf(".") > 3) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Views the collection of clothing available to see (varies by seller, shopper, swapper)
     * @return the viewable clothing (as a list) of clothing
     */
    public Collection<Clothing> viewClothing(){
        return null;
    }
}
