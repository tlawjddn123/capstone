package kr.ac.hansung.cse.method;

import java.util.ArrayList;
import java.util.List;

public class Graph{
    private int n;           //노드들의 수
    private int maps[][];    //노드들간의 가중치 저장할 변수
    private int min1 = 1000;
    public int minIndex = 100;
    private int index;

    private ArrayList<Integer> exitList;
    private ArrayList<Integer> passNode;
    
    public Graph(int n){
        this.n = n;
        maps = new int[n+1][n+1];
         
    }
    public void input(int i,int j,int w){
        maps[i][j] = w;
        maps[j][i] = w;
    }
 
    public List<Integer> dijkstra(int v){
        int distance[] = new int[n+1];          //최단 거리를 저장할 변수
        boolean[] check = new boolean[n+1];     //해당 노드를 방문했는지 체크할 변수
    
        //distance값 초기화.
        for(int i=1;i<n+1;i++){
            distance[i] = Integer.MAX_VALUE;
        }
         
        //시작노드값 초기화.
        distance[v] =0;
        check[v] =true;
         
        //연결노드 distance갱신
        for(int i=1;i<n+1;i++){
            if(!check[i] && maps[v][i] !=0){
                distance[i] = maps[v][i];
            }
        }
         
         
        for(int a=0;a<n-1;a++){ 
            //원래는 모든 노드가 true될때까지 인데 
            //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
            //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
            int min=Integer.MAX_VALUE;
            int min_index=-1;
             
            //최소값 찾기
            for(int i=1;i<n+1;i++){
                if(!check[i] && distance[i]!=Integer.MAX_VALUE){
                    if(distance[i]<min ){
                        min=distance[i];
                        min_index = i;
                        
                    }
                }
            }
            
            check[min_index] = true;
            for(int i=1;i<n+1;i++){
                if(!check[i] && maps[min_index][i]!=0){
                    if(distance[i]>distance[min_index] + maps[min_index][i]){
                        distance[i] = distance[min_index] + maps[min_index][i];
                    }
                }
            }
        }
        exitList = new ArrayList<Integer>();
        exitList.add(distance[1]);
        exitList.add(distance[7]);
        exitList.add(distance[13]);
        exitList.add(distance[17]);
        exitList.add(distance[20]);
        
        for(int i = 0; i<5; i++) {
        	if(min1 > exitList.get(i)) {
        		min1 = exitList.get(i);
        		
        		if(i == 0)minIndex = 1;
        		if(i == 1)minIndex = 7;
        		if(i == 2)minIndex = 13;
        		if(i == 3)minIndex = 17;
        		if(i == 4)minIndex = 20;	
        	}
        }
        System.out.println("minIndex: "+ minIndex);
        passNode = new ArrayList<Integer>();
        //결과값 출력
        index = v;
        for(int i=1;i<n+1;i++){
        	
            if(index > minIndex) {
            	
            	//System.out.print(" passNode1: " + index);
            	passNode.add(index);
            	if(index == 14) {
            		index=12;
            		continue;
            	}
            	if(index == 8) {
            		index = 3;
            		continue;
            	}
            	if(index == 18) {
            		if(minIndex != 17) {
            			index = 16;
            			continue;
            		}
            	}   
            	if(index == 19) { 	
            		index = 16;
           			continue;
            	}
            	
            	
            	index-=1;
            	
            	
            }else if(index < minIndex) {
             	
            	//System.out.print(" passNode5: " + index);
            	passNode.add(index);
            	if(index == 3) {
            		if(minIndex != 7) {
            			index = 8;
            			continue;
            		}
            	}
            	if(minIndex != 7) {
            		if(index == 5 || index == 6 || index == 4) {
            			index -= 1;
            			continue;
            		}
            	}
            	if(index == 12) {
            		if(minIndex > 13) {
            			index = 14;
            			continue;
            		}
            	}
            	if(index == 16) {
            		if(minIndex == 17) {
            			index = 18;
            			continue;
            		}else if(minIndex == 20) {
            			index = 19;
            			continue;
            		}
            	}
            	if(index == 18) {
            		if(minIndex != 17) {
            			index = 16;
            			continue;
            		}
            	}
            	if(index == 19) {
            		if(minIndex != 20) {
            			index = 16;
            			continue;
            		}
            	}
            	index+=1;
       
            }
            else {
            	//System.out.print(" passNode10: " + index);
            	passNode.add(index);
            	break;
            }
            
           
        }
      /* System.out.print("비상구: "+distance[1]+" "+distance[7]+ " "+distance[13]+
       		" "+distance[17]+" "+distance[20]);
       System.out.println();*/
        
       
        return passNode;
         
    }
}

