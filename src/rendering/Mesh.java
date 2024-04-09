package rendering;

import debug.Console;

public class Mesh {

    // Vertex Definition
    private String name;
    private int vao;
    private int vertices;

    // Constructor for basic mesh
    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
        name = STR."\{System.currentTimeMillis()}.\{vao}.\{vertex}";
    }
    public Mesh(int vao, int vertex, String name) {
        this.vao = vao;
        this.vertices = vertex;
        this.name = name;
    }

    // Returns VAO
    public int getVaoID() {
        return vao;
    }

    // Returns total vertices
    public int getVertexCount() {
        return vertices;
    }

    // Returns mesh name
    public String getName(){
        return name;
    }
    public String getNameColoring(){
        return STR."\{Console.BLUE}\{getName()}\{Console.ANSI_RESET}";
    }
    public void setName(String name){
        this.name = name;
    }

}