import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int K = input.nextInt();
        
        Cities allCities = new Cities(N);
        for(int i=0; i<N; i++) {
            City city = new City(i);
            int typeCount = input.nextInt();
            for(int j=0; j<typeCount; j++) {
                city.offerings.add(input.nextInt() - 1);
            }
            allCities.add(i, city);
        }
        for(int i=0; i<M; i++) {
            int a = input.nextInt() - 1;
            int b = input.nextInt() - 1;
            int weight = input.nextInt();
            Cities.connect(allCities.get(a), allCities.get(b));
        }

        System.out.println("determining cityGroups");
        List<Cities> cityGroups = new ArrayList<>();
        for(BigInteger bits=BigInteger.ZERO; !bits.testBit(N); bits=bits.add(BigInteger.ONE)) {
            Cities cities = new Cities();
            for(int c=0; c<N; c++) {
                if(bits.testBit(c)) {
                    cities.add(allCities.get(c));
                }
            }
            Set<Integer> fishTypes = new HashSet<Integer>();
            for(City city: cities){
                fishTypes.addAll(city.offerings);
            }
            if(fishTypes.size()==K){
                cityGroups.add(cities);
            }
        }
        System.out.println(cityGroups);
    }

    private static class City {
        private int id;
        Set<Integer> offerings;
        List<City> neighbors;
        
        public City(int id) {
            this.id = id;
            offerings = new HashSet<>();
            neighbors = new ArrayList<>();
        }
        public int getId() {
            return id;
        }
        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            neighbors.forEach(b::append);
            return String.format("City{id:%d,offerings:%s,neighbors:%s}", id, offerings, b);
        }
    }

    private static class Cities extends ArrayList<City> {
        public Cities() {
            super();
        }
        public Cities(int initialCapacity) {
            super(initialCapacity);
        }
        public static void connect(City a, City b) {
            a.neighbors.add(b);
            b.neighbors.add(a);
        }
    }
}
