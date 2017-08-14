def array = new File("archivo.txt") as String[]
def matriz=new String [array.length][]
for(i=0;i<array.length;i++){
    matriz[i]=array[i].split("[,]")
}
def file = new File("salida.json").withWriter{ writer ->
    writer.writeLine "["
    for(i=1;i<array.length;i++){
        writer.writeLine "    {"
        for(j=0;j<matriz[i].length;j++){
             writer.write "        \""+ matriz[0][j]+"\":\""+matriz[i][j]+"\""
             if (j!=(matriz[i].length-1))
                 writer.writeLine ","
        }
        writer.writeLine ""
        writer.write "    }"
        if (i!=(array.length-1))
            writer.write ","
         writer.writeLine ""
    }
    writer.writeLine "]"
 }
 def archivo = new File("salida.json")
    archivo.eachLine { linea ->
    println linea
}