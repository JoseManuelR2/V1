package puerto;

public class Hub {
    private Contenedor[][] m;

    public Hub(){
        m= new Contenedor[10][12];
    }

    public Contenedor[][] getM(){
        return m;
    }
    public void setM(Contenedor[][] m){
        if(m!=null)this.m=m;
    }
    public Contenedor desapilar(int col){
        if(col<0 || col>=m[0].length) return null;
        for (int i = 0; i < m.length; i++) {
            if(m[i][col]!= null){
                Contenedor aux= m[i][col];
                m[i][col]=null;
                return aux;
            }
        }
        return null;
    }
    public void apilar (Contenedor c){
        if(c!=null){
            int p=c.getPrioridad();
            int col = p-1;
            if(p==1 || p==2){
                for (int i = m.length-1; i >= 0; i--) {
                    if(m[i][col]==null){
                        m[i][col]=c;
                        return;
                    }
                }
            }
            else{
                for (int i = col; i < m[col].length; i++) {
                    for (int j = m.length-1; j >=0 ; j--) {
                        if(m[j][i]==null){
                            m[j][i]=c;
                            return;
                        }
                    }
                }
            }
        }
    }
    public String description(int columna){
        String s;
        for (int i = 0; i < m[columna].length; i++) {
            if(m[i][columna]!=null){
                if(m[i][columna].getContenido()!=null)return m[i][columna].getContenido();
                else return "Description is not set";
            }
        }
        return "Nothing sorted here";
    }

    public int samePais(String s){
        int x=0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j]!=null && m[i][j].getPais().equals(s))x++;
            }
        }
        return x;
    }

    public String toString(){
        String s= new String();

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j]==null) s+="[n]";
                else s+= "[" + m[i][j].getId() + "]";
            }
            s+="\n";
        }

        return s;
    }
    public String cheked (int peso, int number){
        String s="";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if( m[i][j]!=null && m[i][j].getPeso()<=peso && m[i][j].getId()==number) {
                    m[i][j].setInspeccionado();
                    s+=m[i][j].toString();
                }
            }
        }
        return s;
    }
}
