import groovy.json.JsonSlurper

def array = new File("archivo.txt") as String[]
def matriz=new String [array.length][]
for(i=0;i<array.length;i++){
    matriz[i]=array[i].split("[,]")
}
def file
file = "[ \n"
for(i=1;i<array.length;i++){
    file = file + "    { \n"
    for(j=0;j<matriz[i].length;j++){
        file = file + "        \""+ matriz[0][j]+"\":\""+matriz[i][j]+"\""
        if (j!=(matriz[i].length-1))
            file = file + ",\n"
     }
     file = file + "\n"
     file = file + "    }"
     if (i!=(array.length-1))
         file = file+ ","
     file = file + "\n"
}
file=file+ "]"    
def jsonSlurper=new JsonSlurper()
def object = jsonSlurper.parseText(file)
    
//Las siguientes son notas    
    
    println object.size() //regresa el número de objetos
    
    println object[1].keySet() //Regresa la lista de key (encabezado)
    
    println object[1].keySet()[1] //Regresa el key en la posición
    
    println object[1].size() //regresa el número de atributos del objeto
    
    println object[0].values() //Regresa la lista de valores del objeto seleccionado
    
    println object[0].values()[1] //Muestra el valor seleccionado para el objeto seleccionado
    
    println object[0].(object[1].keySet()[2]) //Muestra el valor del key seleccionado para el objeto seleccionado
    
    