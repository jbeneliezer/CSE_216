from rectangle import Rectangle
import math

"""inheriting Rectangle, this class represents a square and includes a snap method to round the square to the 
    nearest integer for each vertex"""


class Square(Rectangle):

    def __init__(self, *floats):
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A square cannot be formed by the given coordinates.")

    def __is_member(self):
        return all(self.side_lengths()[0] == self.side_lengths()[i] for i in range(len(self.side_lengths())))

    @staticmethod
    def actually_round(n):
        if n - math.floor(n) < 0.5:
            return math.floor(n)
        return math.ceil(n)

    def snap(self):
        """Snaps the sides of the square such that each corner (x,y) is modified to be a corner (x',y') where x' is the
        integer value closest to x and y' is the integer value closest to y. This, of course, may change the shape to a
        general quadrilateral, hence the return type. The only exception is when the square is positioned in a way where
        this approximation will lead it to vanish into a single point. In that case, a call to snap() will not modify
        this square in any way."""
        l = []
        for i in self.vertices:
            x = Square.actually_round(i.x)
            y = Square.actually_round(i.y)
            l.append(x)
            l.append(y)
        if all(i == l[0] for i in l):
            return self
        else:
            return Square(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7])
