from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint
from statistics import mean

"""inheriting Quadrilateral, this class represents a rectangle and includes methods for finding the center and area"""


class Rectangle(Quadrilateral):

    def __init__(self, *floats):
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A rectangle cannot be formed by the given coordinates.")

    def __is_member(self):
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        if self.side_lengths()[0] == self.side_lengths()[2] and self.side_lengths()[1] == self.side_lengths()[3]:
            v = list(self.vertices)
            return abs(v[0].x - v[2].x) == abs(v[1].x - v[3].x) and abs(v[0].y - v[2].y) == abs(v[1].y - v[3].y)

    def center(self):
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""
        l_x = [i.x for i in self.vertices]
        l_y = [i.y for i in self.vertices]
        return TwoDPoint(mean(l_x), mean(l_y))

    def area(self):
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""
        l = self.side_lengths()
        return l[0] * l[1]