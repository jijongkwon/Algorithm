import java.util.*;

/*
    1. 많이 재생된 장르 수록
    2. 장르내에서 많이 재생된 노래수록
    3. 고유번호 낮은 노래 수록
    
    풀이
    1. 장르별 총 재생 횟수 계산
    2. 우선순위 큐를 이용해 순서 저장 (장르내 재생횟수, 고유번호)
    3. 장르 재생횟수 순으로 정렬
    4. 각 장르별로 최대 2개 선택
*/
class Solution {
    public int[] solution(String[] genres, int[] plays) {    
        // 1. 장르별 총 재생 횟수 계산
        Map<String, Integer> genrePlayCount = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.put(genres[i], genrePlayCount.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 2. 장르별 노래를 우선순위 큐로 관리
        Map<String, PriorityQueue<Song>> genreSongs = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genreSongs.putIfAbsent(genres[i], new PriorityQueue<>());
            genreSongs.get(genres[i]).offer(new Song(i, plays[i]));
        }
        
         // 3. 장르를 총 재생 횟수 순으로 정렬
        List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
        sortedGenres.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));
        
        // 4. 각 장르별로 최대 2곡씩 선택
        List<Integer> answer = new ArrayList<>();
        for (String genre : sortedGenres) {
            PriorityQueue<Song> pq = genreSongs.get(genre);
            int count = 0;
            while (!pq.isEmpty() && count < 2) {
                answer.add(pq.poll().id);
                count++;
            }
        }
        
        int[] songs = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            songs[i] = answer.get(i);
        }
        
        return songs;
    }
    
    class Song implements Comparable<Song> {
        int id;
        int plays;
        
        Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song other) {
            if (this.plays != other.plays) {
                return other.plays - this.plays;  // 재생 횟수 내림차순
            }
            return this.id - other.id;  // 고유번호 오름차순
        }
    }
}