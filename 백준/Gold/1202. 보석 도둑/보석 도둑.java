import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] bags;
    static Product[] products;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bags = new int[k];

        products = new Product[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            products[i] = new Product(weight, value);
        }

        // product 정렬
        // 무게가 가벼운 순 -> 가치가 높은 순
        Arrays.sort(products, (o1, o2) ->{
            if(o1.weight == o2.weight){
                return Integer.compare(o1.value, o2.value) *-1;
            }

            return Integer.compare(o1.weight, o2.weight);
        });

        for(int i = 0; i < k; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 가방 가벼운 순으로 정렬
        Arrays.sort(bags);

        // 이제 가벼운 것부터 꺼내는데 가중치를 기준으로 꺼내서 최대값을 구함
        // pq 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) ->{
            return Integer.compare(o1, o2) *-1;
        });

        long answer = 0;
        int index = 0;
        for(int i = 0 ; i < k ; i++){
            // 가방에 담을 수 있다면 ? 받아오기
            while(index < n && products[index].weight <= bags[i]){
                pq.add(products[index++].value);
            }

            // 그리고 하나씩 꺼냄 -> 계속 value가 갱신되서 최고값만 뽑아올 수 있음
            if(!pq.isEmpty()){
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }

    static class Product{
        int weight;
        int value;

        public Product(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }
}