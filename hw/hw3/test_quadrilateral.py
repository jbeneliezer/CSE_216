from unittest import TestCase
from quadrilateral import Quadrilateral

""" class implementing unittest to test Quadrilateral class methods __init__, __eq__, __ne__, __add__, __sub__,
    side_lengths, and smallest_x"""


class TestQuadrilateral(TestCase):
    def test_init(self):
        with self.assertRaises(Exception):
            Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 0, 4)

    def test_eq(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 0, 5)
        b = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 0, 5)
        self.assertEqual(a, b)

    def test_ne(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 5, 0)
        b = Quadrilateral(5, 1, 1, 1, 1, 0, 5, 0)

    def test_add(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 5, 0)
        b = Quadrilateral(5, 1, 1, 1, 1, 0, 5, 0)
        c = Quadrilateral(11, 2.5, 2, 2.5, 1, 0, 10, 0)
        self.assertEqual((a + b), c)

    def test_sub(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 5, 0)
        b = Quadrilateral(5, 1, 1, 1, 1, 0, 5, 0)
        c = Quadrilateral(1, 0.5, 0, 0.5, -1, 0, 0, 0)
        self.assertEqual((a - b), c)

    def test_side_lengths(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 5, 0)
        v = list(a.vertices)
        l = list(a.side_lengths())
        self.assertEqual((v[0].x - v[1].x), l[0].x)
        self.assertEqual((v[0].y - v[1].y), l[0].y)
        self.assertEqual((v[0].x - v[3].x), l[1].x)
        self.assertEqual((v[0].y - v[3].y), l[1].y)
        self.assertEqual((v[3].x - v[2].x), l[2].x)
        self.assertEqual((v[3].y - v[2].y), l[2].y)
        self.assertEqual((v[1].x - v[2].x), l[3].x)
        self.assertEqual((v[1].y - v[2].y), l[3].y)

    def test_smallest_x(self):
        a = Quadrilateral(6, 1.5, 1, 1.5, 0, 0, 5, 0)
        b = Quadrilateral(5, 1, 1, 1, 1, 0, 5, 0)
        self.assertEqual(a.smallest_x(), 0)
        self.assertEqual(b.smallest_x(), 1)
