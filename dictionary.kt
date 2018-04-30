import java.io.File
public var dict: MutableMap<String, String>? = null

fun srch(k: String,v: String){
    dict?.filter{ it.key.contains(k.trim()) && it.value.contains(v.trim())}?.forEach { println("${it.key} - ${it.value}") }

}

public fun main(args: Array<String>) {
    dict = mutableMapOf()
    File("dict.txt").createNewFile()
    File("dict.txt").forEachLine(){
        val c = it.split('-')
        dict!!.put(c[0].trim(),c[1].trim())
    }
    var a = readLine()
    while (!a.equals("end")){ when(a?.split(" ")?.get(0)){
        "del" ->{
            val pair = a.drop(4).split("-")
            if (pair.size == 2) dict!!.remove(pair[0].trim(),pair[1].trim())
            else println("В строке должно быть 1 символ \"-\" разделяющий ключ и значение")

        }
        "delk" ->{
            val single = a.drop(5)
            if (!single.contains('-')) dict!!.remove(single.trim())
            else println("В строке не должно быть символа \"-\"")

        }
        "delv" ->{
            val single = a.drop(5)
            val suit = dict!!.filterValues {it == single.trim() }
            suit.forEach {
                println("${it.key} Удалить этот ключ, привязанный к искомому значению?<Y/N>")
                var ch = readLine()
                while (true){
                    if (ch == "Y") {
                        dict!!.remove(it.key)
                        break
                    }
                    else if (ch == "N") {
                        break
                    }
                    else println("Введите 'Y', если хотите удалить пару с этим ключом, иначе 'N'.")
                }
            }
        }
        "srch" ->{
            val pair = a.drop(5).split("-")
            if (pair.size == 2) srch(pair[0].trim(),pair[1].trim())
            else println("В строке должно быть 1 символ \"-\" разделяющий ключ и значение")

        }
        "srchk" ->{
            val single = a.drop(6)
            if (!single.contains('-')) srch( single.trim(), "")
            else println("В строке не должно быть символа \"-\"")

        }
        "srchv" ->{
            val single = a.drop(6)
            if (!single.contains('-')) srch( "" ,single.trim())
            else println("В строке не должно быть символа \"-\"")

        }
        "add" ->{
            val pair = a.drop(4).split("-")
            if (pair.size == 2) dict!!.put(pair[0].trim(),pair[1].trim())
            else println("В строке должно быть 1 символ \"-\" разделяющий ключ и значение")


        }
        else ->{
            println("Неверная команда. Существующие команды:")
            println("add КЛЮЧ - ЗНАЧЕНИЕ - добавление в словарь пары ключ-значение. Если ключ уже состоит в паре, то он станет привязан к новому значению.")
            println("del КЛЮЧ - ЗНАЧЕНИЕ - удаление из словаря пары ключ-значение, если есть подходящая.")
            println("delk КЛЮЧ - удаление пары ключ-значение с данным ключом, если такой есть.")
            println("delv ЗНАЧЕНИЕ - поиск и предложение на удаление пар с подходящем значением.")
            println("srch ФРАГМЕНТ_КЛЮЧА - ФРАГМЕНТ_ЗНАЧЕНИЯ - поиск пар с ключами и значения, содержащими данные фрагментами.")
            println("srchv ФРАГМЕНТ_ЗНАЧЕНИЯ - поиск пар со значениемя, содержащими данный фрагмент.")
            println("srchk ФРАГМЕНТ_КЛЮЧ - поиск пар с ключами, содержащими данный фрагмент.")
            println("end - завершение сессии и сохранение данных.")


        }}
        a = readLine()
            }
    File("dict.txt").delete()
    File("dict.txt").createNewFile()
    File("dict.txt").printWriter().use { out ->
        dict!!.forEach {
            out.println("${it.key}-${it.value}")
        }
    }
}