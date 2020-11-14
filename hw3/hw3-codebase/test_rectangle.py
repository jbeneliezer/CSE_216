from unittest import TestCase
from rectangle import Rectangle
from two_d_point import TwoDPoint

"""this class tests Rectangle objects, particularly their center and area methods"""


class TestRectangle(TestCase):

    def test_is_member(self):
        a = Rectangle(5, 1, 1, 1, 1, 0, 5, 0)
        with self.assertRaises(TypeError):
            b = Rectangle(6, 1.5, 1, 1.5, 0, 0, 5, 0)

    def test_center(self):
        a = Rectangle(5, 1, 1, 1, 1, 0, 5, 0)
        self.assertEqual(a.center(), TwoDPoint(3, 0.5))

    def test_area(self):
        a = Rectangle(5, 1, 1, 1, 1, 0, 5, 0)
        self.assertEqual(a.area(), 4)
