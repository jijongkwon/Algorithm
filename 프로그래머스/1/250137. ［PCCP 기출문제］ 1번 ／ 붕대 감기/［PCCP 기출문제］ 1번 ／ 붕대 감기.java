/*
 t초 -> 1초마다 x만큼 회복
 t초 연속으로 붕대 감으면 y만큼 추가 회복
 
 기술 사용 중 공격 받으면 취소
 공격 당하는 순간 체력회복 X
 
 취소 or 기술 끝 -> 붕대감기 사용, 연속 성공 시간 0
 
 공격 받으면 피해량만큼 체력이 줌
 - 체력이 0이면 사망 ( 체력회복 불가 )
 
 모든 공격이 끝난 직후 남은 체력 return ( 체력 ? 체력 : -1)
*/
class Solution {
    int maxHealth;
    int successTarget;
    int addHealth;
    int bonusHealth;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        // init
        successTarget = bandage[0];
        addHealth = bandage[1];
        bonusHealth = bandage[2];
        maxHealth = health;
        
        User user = new User(maxHealth, 0);
        
        // 시간 구하기
        int time = attacks[attacks.length - 1][0];
        
        int attackIdx = 0;
        for(int i = 1; i <= time; i++){
            int attackTime = attacks[attackIdx][0];
            int damage = attacks[attackIdx][1];
            
            // 공격 시간이 일치하면
            if(i == attackTime){
                user.attackedFromMonstart(damage);
                
                if(attackIdx < attacks.length){
                    attackIdx++;
                }
                
                if(user.curHealth <= 0){
                    return -1;
                }
                
                continue;
            }
            
            // 회복
            user.healling();
        }
        
        return user.curHealth;
    }
    
    // 유저
    class User{
        int curHealth;
        int successCount;

        User(int curHealth, int successCount){
            this.curHealth = curHealth;
            this.successCount = successCount;
        }

        // 공격받음
        void attackedFromMonstart(int damage){
            this.curHealth -= damage;
            this.successCount = 0;
        }

        // 회복기술 사용
        void healling(){
            this.curHealth += addHealth;
            successCount++;

            if(successCount == successTarget){
                this.curHealth += bonusHealth;
                this.successCount = 0;
            }

            // 최대 체력이면 회복 x
            if(this.curHealth > maxHealth){
                this.curHealth = maxHealth;
            }
        }
    }
}