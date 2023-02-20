#version 330

out vec4 fragColor;
uniform vec4 uni_color;
in vec4 out_color;
//Buat mewarnai garis/titik di layar
void main() {
    //    fragColor = uni_color;
    //    rgba: red 100/255
    //    vec4(red, green, blue, alpha)
    //    fragColor = vec4(1.0f, 0.0f, 0.0f, 1.0f);
    fragColor = uni_color;
}