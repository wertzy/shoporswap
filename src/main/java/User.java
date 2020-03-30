import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class User {
    protected String accountName;
    protected String password;
    protected List<String> transactionHistory;
    protected double rating;

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

    public String getAccountName(){
        return this.accountName;
    }
    public String getPassword(){
        return this.password;
    }
    public List <String> getTransactionHistory(){return this.transactionHistory;}
    public double getRating(){return this.rating;}

    public static boolean isAccountNameValid(String accountName) {
        return false;
    }
    public static boolean isPasswordValid(String password) {
        return false;
    }

    public static boolean isRatingValid(double rating) {
        return false;
    }
}
