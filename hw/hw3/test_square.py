from unittest import TestCase
from square import Square

"""this class tests the Square class' snap method, checking to make sure the vertices snap to the nearest integer
    unless doing so would create a single point rather than a square"""


class TestSquare(TestCase):

    def test_snap(self):
        a = Square(4.1, 4.1, 0, 4.1, 0, 0, 4.1, 0)
        b = Square(4, 4, 0, 4, 0, 0, 4, 0)
        self.assertEqual(a.snap(), b)
        c = Square(0.1, 0.1, 0, 0.1, 0, 0, 0.1, 0)
        self.assertEqual(c.snap(), c)