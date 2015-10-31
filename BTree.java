/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;


public class BTree<Key extends Comparable<Key>,Value>{
    
    private Node Root;
    private int HT;
    private static final int M=4;
    private  int N;

  
    
    private static final class Node{
       private int m;
       Entry Children[]=new Entry[M];

        public Node(int k) {
            m=k;
        }
    }
    
    private static class Entry{
       private Comparable key;
       private Object value;
       private Node Next;
        public Entry(Comparable key,Object value,Node next){
            this.key=key;
            this.value=value;
            this.Next=next;
        }
    }
    
    public BTree(){
        Root=new Node(0);
    }
    
    public Value get(Key key){
       return search(Root,key,HT);
       
    }
    
     private Value search(Node Root, Key key, int HT) {
         Entry etr[]=Root.Children;
         
         if(HT==0){
             for(int i=0;i<Root.m;++i){
                 if(equ(key,etr[i].key))
                  return (Value)etr[i].value;
             }
         }
         
         else{
             for(int i=0;i<Root.m;++i){
                 if(i+1==Root.m||less(key, etr[i+1].key))
                     return  search(etr[i].Next, key, HT-1); 
             }
           }
     return null;
    }
     
private boolean equ(Comparable key1, Comparable key2) {
return key1.compareTo(key2)==0; 
}

    
    public void put(Key key,Value value){
        Node u=insert(Root,key,value,HT);
        ++N;
        if(u==null)
            return;
        Node nwd=new Node(2);  
        nwd.Children[0]=new Entry(Root.Children[0].key, null,Root);
        nwd.Children[1]=new Entry(u.Children[0].key, null, u);
        ++HT;
        Root=nwd;
    }
    
    private Node insert(Node Root, Key key, Value value, int HT) {
        Entry etry=new Entry(key, value, null);
        int j;
        if(HT==0){
            for(j=0;j<Root.m;++j ){
                if(less(key,Root.Children[j].key)) break;
            }
        }
        
        else{
            for(j=0;j<Root.m;++j){
                if(j+1==Root.m||less(key, Root.Children[j+1].key)){
                    Node u=insert(Root.Children[j++].Next, key, value, HT-1);
                    if(u==null)
                        return null;
                    etry.key=u.Children[0].key;
                    etry.Next=u;
                    break;
                }
            }
        }
        for(int i=Root.m;i>j;--i){
            Root.Children[i]=Root.Children[i-1];
        }
        Root.Children[j]=etry;
        Root.m++;
        if(Root.m<M){ 
            return null;
        }
        else
        return split(Root);
    }
    
    private Node split(Node Root) {
        Node nw=new Node(M/2);
        Root.m=M/2;
        for(int i=0;i<M/2;++i)
            nw.Children[i]=Root.Children[M/2 + i];
        return nw;
    }

    
     private boolean less(Comparable key1, Comparable key2) {
         return key1.compareTo(key2)<0;
    }
}
