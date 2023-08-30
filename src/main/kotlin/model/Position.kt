package model

data class Position(val x: Int, val y: Int, val direction: Direction, val isLost: Boolean = false)