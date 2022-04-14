import java.lang.reflect.*;

public class Pilha <X> implements Cloneable{

    private Object[] elemento; // X[]
    private int tamanhoInicial;
    private int ultimo=-1; // vazio

    public Pilha (int tamanho) throws Exception{
        if (tamanho<=0)
            throw new Exception("Tamanho inválido");
        this.elemento = new Object[tamanho]; // X[]
        this.tamanhoInicial = tamanho;
    }

    public Pilha (){
        this.elemento = new Object[10]; // X[10]
        this.tamanhoInicial = 10;
    }

    private void redimensioneSe (float fator){
        // copia todo o this.elemento dentro do array novo
        Object[] novo = new Object[Math.round(this.elemento.length*fator)];

        for (int i = 0; i < this.ultimo; i++)
            novo[i] = this.elemento[i];

        this.elemento = novo;
    }

    private X meuCloneX (X x){
        X ret = null;

        try{
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone",tipoDosParms);
            Object[] parms = null;
            ret = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }

    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2.0F);

        this.ultimo++;

        if (x instanceof Cloneable)
            this.elemento[this.ultimo]=meuCloneX(x);
        else
            this.elemento[this.ultimo]=x;
    }

    public X recupereUmItem () throws Exception
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.ultimo] instanceof Cloneable)
            ret = meuCloneX((X)this.elemento[this.ultimo]);
        else
            ret = (X)this.elemento[this.ultimo];

        return ret;
    }

    public void removaUmItem () throws Exception
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length>this.tamanhoInicial &&
                this.ultimo+1<=Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }

    public boolean isCheia ()
    {
        if(this.ultimo+1==this.elemento.length)
            return true;

        return false;
    }

    public boolean isVazia ()
    {
        if(this.ultimo==-1)
            return true;

        return false;
    }

    @Override
    public String toString ()
    {
        String ret = (this.ultimo+1) + " elemento(s)";

        if (this.ultimo!=-1)
            ret += ", sendo o ultimo "+this.elemento[this.ultimo];

        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if(this.ultimo!=pil.ultimo)
            return false;

        if(this.tamanhoInicial!=pil.tamanhoInicial)
            return false;

        for(int i=0 ; i<this.ultimo;i++)
            if(!this.elemento[i].equals(pil.elemento[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=999;

        ret = ret*7 + new Integer(this.ultimo        ).hashCode();
        ret = ret*7 + new Integer(this.tamanhoInicial).hashCode();

        for (int i=0; i<this.ultimo; i++)
            ret = ret*7 + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    // construtor de copia
    public Pilha (Pilha<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo ausente");

        this.tamanhoInicial = modelo.tamanhoInicial;
        this.ultimo         = modelo.ultimo;
        this.elemento = new Object[modelo.elemento.length]; // X [modelo.elemento.length];

        for(int i=0 ; i<modelo.elemento.length ; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    @Override
    public Object clone ()
    {
        Pilha<X> ret=null;

        try
        {
            ret = new Pilha<X>(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }

}
