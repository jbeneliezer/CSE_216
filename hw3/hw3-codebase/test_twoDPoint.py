from unittest import TestCase
from two_d_point import TwoDPoint

"""this class tests TwoDPoint objects, and in particular, the from_coordinates method"""


class TestTwoDPoint(TestCase):

    def test_eq(self):
        a = TwoDPoint(1, 2)
        b = TwoDPoint(1, 2)
        self.assertEqual(a, b)

    def test_ne(self):
        a = TwoDPoint(1, 2)
        b = TwoDPoint(1, 3)
        self.assertNotEqual(a, b)

    def test_add(self):
        a = TwoDPoint(1, 2)
        b = TwoDPoint(1, 3)
        c = TwoDPoint(2, 5)
        self.assertEqual((a+b), c)

    def test_sub(self):
        a = TwoDPoint(1, 2)
        b = TwoDPoint(1, 3)
        c = TwoDPoint(0, -1)
        self.assertEqual((a - b), c)

    def test_from_coordinates(self):
        a = TwoDPoint(1, 2)
        b = TwoDPoint(1, 3)
        c = TwoDPoint(0, -1)
        d = TwoDPoint(5, 6.5)
        l = [a, b, c, d]
        l2 = [1, 2, 1, 3, 0, -1, 5, 6.5]
        self.assertEqual(l, TwoDPoint.from_coordinates(l2))
