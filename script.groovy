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
  /*  
    println object.size() //regresa el n�mero de objetos
    
    println object[1].keySet() //Regresa la lista de key (encabezado)
    
    println object[1].keySet().join(",") //Une la lista usando ","
    
    println object[1].keySet()[1] //Regresa el key en la posici�n
    
    println object[1].size() //regresa el n�mero de atributos del objeto
    
    println object[0].values() //Regresa la lista de valores del objeto seleccionado
    
    println object[0].values()[1] //Muestra el valor seleccionado para el objeto seleccionado
    
    println object[0].(object[1].keySet()[2]) //Muestra el valor del key seleccionado para el objeto seleccionado*/
    
    
    
    //Ejemplo que genera todos los insert
    
    for (i=0;i<object.size();i++){
        def atributos=object[i].keySet().join(",")
        for (j=0;j<object[i].size();j++)
            try{
                Float.parseFloat(object[i].values()[j])
            }catch(NumberFormatException e){
                object[i].put(object[i].keySet()[j],"'"+object[i].values()[j]+"'")
            }
        def values=object[i].values().join(",")
        println "INSERT INTO tabla ("+atributos+") VALUES ("+values+")"
    }
    