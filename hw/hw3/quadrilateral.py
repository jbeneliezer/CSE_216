import math
from two_d_point import TwoDPoint

""" class representing a quadrilateral object, includes method side_lengths for getting a tuple of the side lengths and 
    smallest_x for getting smallest x point of quadrilateral"""

class Quadrilateral:

    def __init__(self, *floats):
        points = TwoDPoint.from_coordinates(list(floats))
        if len(points) < 4:
            raise Exception
        self.__vertices = tuple(points[0:4])
        if not self.__is_member():
            raise Exception

    def __is_member(self):
        s = self.side_lengths()
        return s[0] + s[1] + s[2] > s[3]

    def __eq__(self, other: object):
        if not isinstance(other, Quadrilateral):
            return False
        return self.vertices == other.vertices

    def __ne__(self, other: object):
        if not isinstance(other, Quadrilateral):
            return False
        return not self.vertices == other.vertices

    def __str__(self):
        s = ""
        for i in self.vertices:
            s += f'({i.x},{i.y})'
        return s

    def __add__(self, other: object):
        if not isinstance(other, Quadrilateral):
            raise Exception
        l = []
        for i in range(len(self.vertices)):
            l.append(self.vertices[i].x + other.vertices[i].x)
            l.append(self.vertices[i].y + other.vertices[i].y)
        return Quadrilateral(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7])

    def __sub__(self, other: object):
        if not isinstance(other, Quadrilateral):
            return False
        l = []
        for i in range(len(self.vertices)):
            l.append(self.vertices[i].x - other.vertices[i].x)
            l.append(self.vertices[i].y - other.vertices[i].y)
        return Quadrilateral(l[0], l[1], l[2], l[3], l[4], l[5], l[6], l[7])

    @property
    def vertices(self):
        return self.__vertices

    def side_lengths(self):
        """Returns a tuple of four floats, each denoting the length of a side of this quadrilateral. The value must be
        ordered clockwise, starting from the top left corner."""
        l = list(self.vertices)
        a = l[0] - l[1]
        b = l[0] - l[3]
        c = l[3] - l[2]
        d = l[1] - l[2]
        return math.sqrt(a.x ** 2 + a.y ** 2), math.sqrt(b.x ** 2 + b.y ** 2), math.sqrt(c.x ** 2 + c.y ** 2), \
            math.sqrt(d.x ** 2 + d.y ** 2)

    def smallest_x(self):
        """Returns the x-coordinate of the vertex with the smallest x-value of the four vertices of this
        quadrilateral."""
        l = list(self.vertices)
        l.sort(key=lambda a: a.x)
        return l[0].x
