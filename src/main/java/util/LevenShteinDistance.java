/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author polyfish
 */
public class LevenShteinDistance {
    
    
    public int calcDist(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        
        int[][] lev = new int[aLen + 1][bLen + 1];
        
        for (int i = 0; i <= aLen; ++i) {
            for (int j = 0; j <= bLen; ++j) {
                if (Math.min(i, j) == 0) {
                    lev[i][j] = Math.max(i, j);
                }
                else {
                    int delta = (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1);
                    lev[i][j] = Math.min(lev[i - 1][j] + 1, lev[i][j - 1] + 1);
                    lev[i][j] = Math.min(lev[i][j], lev[i - 1][j - 1] + delta);
                }
            }
        }
        
        return lev[aLen][bLen];
    }
    
    public static void main(String[] args) {
        String a = "fuckerr";
        String b = "fuckerr";
        
        LevenShteinDistance metric = new LevenShteinDistance();
        System.out.println(metric.calcDist(a, b));
    }
}
