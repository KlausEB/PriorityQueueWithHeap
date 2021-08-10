import java.util.*;

//Первая строка входа содержит число операций 1≤n≤10^5. Каждая из последующих n строк задают операцию одного из следующих двух типов:

//Insert x, где 0≤x≤10^9 — целое число;
//ExtractMax.

//Первая операция добавляет число xx в очередь с приоритетами, вторая — извлекает максимальное число и выводит его

//Test Input:
//6
//Insert 200
//Insert 10
//ExtractMax
//Insert 5
//Insert 500
//ExtractMax

//Output: 
//200
//500

class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PQ pq = new PQ(n);
        for (int i = 0; i < n; i++) {
            if (scanner.next().equals("Insert")){
                pq.insert(scanner.nextInt());
            } else {
                System.out.println(pq.extractMax());
            }
        }
    }


}

class PQ {
    int size = 0;
    public int[] H;

    public PQ(int size) {
        this.H = new int[size];
    }

    public void insert(int p){
        H[size] = p;
        siftUp(size);
        size++;
    }

    public int extractMax(){
        int result = H[0];
        H[0] = H[size-1];
        size--;
        siftDown(0);
        return result;
    }

    public void siftDown(int i){
        while (true) {
            int minIndex = i;
            int l = left(i);
            if (l < size) {
                if (H[l] > H[minIndex]) {
                    minIndex = l;
                }
            }
            int z = right(i);
            if (z < size) {
                if (H[z] > H[minIndex]) {
                    minIndex = z;
                }
            }
            if (i != minIndex) {
                int buffer = H[i];
                H[i] = H[minIndex];
                H[minIndex] = buffer;
                i = minIndex;
                continue;
            }
            break;
        }
    }

    public void siftUp(int i){
        while(i > 0 && H[parent(i)] < H[i]){
            int buffer = H[parent(i)];
            H[parent(i)] = H[i];
            H[i] = buffer;
            i = parent(i);
        }
    }

    public int parent(int i){
        return (i-1)/2;
    }

    public int left(int i){
        return 2*i + 1;
    }

    public int right(int i){
        return 2*i + 2;
    }
}
