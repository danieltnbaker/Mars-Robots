package ext

import model.Action
import model.Direction

fun String.getX() = trimSpace().substring(0, 1).toInt()
fun String.getY() = trimSpace().substring(1, 2).toInt()
fun String.getDirection() = Direction.entries.firstOrNull { it.initial == trimSpace().substring(2, 3) }
fun String.getAction() = Action.entries.firstOrNull { it.action == this }
fun String.trimSpace() = replace(" ", "")