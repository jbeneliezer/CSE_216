from quadrilateral import Quadrilateral

"""this class is used for sorting Quadrilateral objects, based on their smallest x value"""


class ShapeSorter:
    @staticmethod
    def sort(*args: Quadrilateral):
        l = []
        for i in args:
            l.append(i)
        return sorted(l, key=lambda x: x.smallest_x()) # sort based on smallest_x algorithm
