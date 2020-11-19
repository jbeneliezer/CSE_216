from typing import List

"""class that represents a point on a two dimensional plane and includes static method  from_coordinates to convert
    list of floats to list of two d poins"""


class TwoDPoint:

    def __init__(self, x, y) -> None:
        self.__x = x
        self.__y = y

    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    def __eq__(self, other: object) -> bool:
        if not isinstance(other, TwoDPoint):
            return False
        return self.__x == other.__x and self.__y == other.__y

    def __ne__(self, other: object) -> bool:
        if not isinstance(other, TwoDPoint):
            return False
        return not self.__eq__(other)

    def __str__(self) -> str:
        return f'({self.__x}, {self.__y})'

    # TODO: add magic methods such that two TwoDPoint objects can be added and subtracted coordinate-wise just by using
    #  syntax of the form p + q or p - q

    def __add__(self, other: object):
        if not isinstance(other, TwoDPoint):
            return False
        return TwoDPoint(self.__x + other.__x, self.__y + other.__y)

    def __sub__(self, other: object):
        if not isinstance(other, TwoDPoint):
            return False
        return TwoDPoint(self.__x - other.__x, self.__y - other.__y)

    @staticmethod
    def from_coordinates(coordinates: List[float]):
        if len(coordinates) % 2 != 0:
            raise Exception("Odd number of floats given to build a list of 2-d points")
        points = []
        it = iter(coordinates)
        for x in it:
            points.append(TwoDPoint(x, next(it)))
        return points
