package Engine;

import org.joml.Vector3f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object2d extends ShaderProgram {

    List<Vector3f> vertices;
    int vao;
    int vbo;

    public Object2d(List<ShaderModuleData> shaderModuleData
            , List<Vector3f> vertices) {
        super(shaderModuleData);
        this.vertices = vertices;
        setupVAOVBO();
    }

    public void setupVAOVBO() {
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);
    }

    public void drawSetup() {
        bind();

        //Bind VBO
        glEnableVertexAttribArray(0); //Mau mengirim data vertex yang sudah disimpan ke vbo index ke 0
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); //Tipe data GL_FLOAT tergantung dengan apa yang kita kirim (Tipe datanya float primitif)
        //Pointer sama aja kyk First, jadi mulai dari index ke 0
    }

    public void draw() {
        drawSetup();
        //Draw the vertices
        //Optional
        glLineWidth(1); //Mengatur ketebalan
        glPointSize(0); //Mengatur besar/kecil vertex

        //Wajib
        //Ada:
        //GL_LINE
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLES_FAN
        //GL_POINT
        glDrawArrays(GL_LINE_LOOP, 0, vertices.size()); //Mengatur custom/tipe seperti apa, kalau garis: GL_Line, kalau segitiga GL_Triangle
        //First disini berarti index pertama kita menggambar titik tersebut
        //Nah kalau ingin menentukan banyak titik, pakai vertices.size(): untuk menggambar semua, kalau cuma mau gambar 2 titik, ketik angka 2
    }
}
