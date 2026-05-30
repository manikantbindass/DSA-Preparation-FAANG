class Solution{
    static class Seg {
        int n;
        int[] t;
        Seg(int m) {
            n = 1;
            while (n < m) n <<= 1;
            t = new int[n << 1];
        }
        void upd(int p, int v) {
            p += n;
            t[p] = v;
            for (p >>= 1; p > 0; p >>= 1)
                t[p] = Math.max(t[p << 1], t[p << 1 | 1]);
        }
        int qry(int l, int r) {
            int a = 0;
            l += n;
            r += n;
            while (l <= r) {
                if ((l & 1) == 1) a = Math.max(a, t[l++]);
                if ((r & 1) == 0) a = Math.max(a, t[r--]);
                l >>= 1;
                r >>= 1;
            }
            return a;
        }
    }
    public List<Boolean> getResults(int[][] queries){
        int m = 150000 + 5;
        TreeSet<Integer> s = new TreeSet<>();
        s.add(0);
        s.add(m);
        Seg g = new Seg(m + 1);
        g.upd(m, m);
        List<Boolean> r = new ArrayList<>();
        for (int[] q : queries){
            if (q[0] == 1){
                int x = q[1];
                int a = s.lower(x);
                int b = s.higher(x);
                g.upd(b, b - x);
                g.upd(x, x - a);
                s.add(x);
            } else{
                int x = q[1];
                int z = q[2];
                Integer p = s.floor(x);
                int mx = 0;
                if (p != null)
                    mx = x - p;
                mx = Math.max(mx, g.qry(0, x));
                r.add(mx >= z);
            }
        }
        return r;
    }
}
