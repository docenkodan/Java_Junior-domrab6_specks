package test;

public class Test {
	
	int[][] pos = 
	{
	    {3, 3}, {0, 0}, {0, 1}, {0, 2},
	    {0, 3}, {1, 0}, {1, 1}, {1, 2},
	    {1, 3}, {2, 0}, {2, 1}, {2, 2},
	    {2, 3}, {3, 0}, {3, 1}, {3, 2}
	};

	int[] dx = { 0, 1, 0, -1};
	int[] dy = {-1, 0, 1, 0};
	char[] c = {'L','D','R','U'};
	int[] rev= {2, 3, 0, 1};

	//typedef struct
	//{
	    int[][] a = new int [4][4];
	//} board;

	int[][][][] d = new int [4][4][4][4];
	vector <char> res;

	board BD;
	boolean have_answer;

	int max_dist;

	int ab(int x) {return x >= 0 ? x : -x;}

	void calc_d()
	{
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	            for (int r = 0; r < 4; r ++)
	                for (int s = 0; s < 4; s ++)
	                    d[i][j][r][s] = ab(r - i) + ab(s - j);
	}

	int dist(board X)
	{
	    int res = 0;
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	            if (X.a[i][j] != 0) res += d[i][j][pos[X.a[i][j]][0]][pos[X.a[i][j]][1]];
	    return res;
	}

	boolean val(int p, int q)
	{
	    if (p >= 0 && q >= 0 && p < 4 && q < 4) return true;
	    return false;
	}

	void rec(int g, int x0, int y0, int prev)
	{
	    if (have_answer) return;
	    int h = dist(BD);
	    if (h == 0)
	    {
	        for (int i = 0; i < res.size(); i ++) printf("%c", res[i]);
	        System.out.printf("\n");
	        have_answer = true;
	        return;
	    }
	    if (g + h > max_dist) return;

	    for (int i = 0; i < 4 && !have_answer; i ++)
	        if (val(x0 + dx[i], y0 + dy[i]) && rev[i] != prev)
	        {
	            swap(BD.a[x0][y0], BD.a[x0 + dx[i]][y0 + dy[i]]);
	            res.push_back(c[i]);
	            rec(g + 1, x0 + dx[i], y0 + dy[i], i);
	            res.pop_back();
	            swap(BD.a[x0][y0], BD.a[x0 + dx[i]][y0 + dy[i]]);
	        }
	}

	boolean solution()
	{
	    int sum = 0;
	    int[] a = new int [16]; 
	    int k = -1;
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	        {
	        	 k ++;
	        	 a[k] = BD.a[i][j];
	        }
	    for (int i = 0; i < 16; i ++)
	        for (int j = i; j < 16; j ++)
	            if (a[j] != 0 && a[j] < a[i]) sum ++;
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	            if (BD.a[i][j] == 0) sum += i + 1;

	    return (sum % 2) ? false : true;
	}

	void solve()
	{
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	            scanf("%d", &BD.a[i][j]);

	    if (!solution())
	    {
	    	System.out.printf("This puzzle is not solvable.\n");
	        return;
	    }

	    res.resize(0);
	    have_answer = false;
	    int x0, y0;
	    for (int i = 0; i < 4; i ++)
	        for (int j = 0; j < 4; j ++)
	            if (BD.a[i][j] == 0) x0 = i, y0 = j;

	    int s = dist(BD);
	    for (int i = s; i <= 50 && !have_answer; i ++)
	    {
	        max_dist = i;
	        rec(0, x0, y0, -1);
	    }
	    
	    if (!have_answer)
	    {
	        System.out.printf("No solutions found.\n");
	    }
	}
	
}
