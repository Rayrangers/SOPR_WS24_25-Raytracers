package rayrangers.raytracer.world;

import java.util.List;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a triangle as a special face.
 */
public class Triangle extends Face {

    /**
     * Array containing all vertices of the triangle.
     */
    private Vertex3D[] vertices;

    /**
     * Class constructor specifying the material, smoothing group and the vertices
     * of the triangle.
     * 
     * @param material       material
     * @param smoothingGroup smoothing group
     * @param v1             vertex 1
     * @param v2             vertex 2
     * @param v3             vertex 3
     * @see Face
     */
    public Triangle(Material material, String smoothingGroup, Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        super(material, smoothingGroup);
        vertices = new Vertex3D[] { v1, v2, v3 };
    }

    /**
     * Class constructor called by ObjParser.
     * 
     * @param material       material
     * @param smoothingGroup smoothing group
     * @param vertices       vertex list
     */
    public Triangle(Material material, String smoothingGroup, List<Vertex3D> vertices) {
        super(material, smoothingGroup);
        this.vertices = new Vertex3D[] { vertices.get(0), vertices.get(1), vertices.get(2) };
    }

    /**
     * Returns all vertices of the triangle in an array.
     * 
     * @return vertices
     */
    public Vertex3D[] getAllVert() {
        return vertices;
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Triangle ABC with vertices A, B, C
        // E = origin of the ray (camera eye)
        Vector3D vecBA = vertices[0].getlocationVector().sub(vertices[1].getlocationVector()); // Vector BA = (a,b,c)^T
        Vector3D vecCA = vertices[0].getlocationVector().sub(vertices[2].getlocationVector()); // Vector CA = (d,e,f)^T
        Vector3D vecEA = vertices[0].getlocationVector().sub(ray.getOrigin().getlocationVector()); // Vector EA = (g,h,i)^T
        Vector3D rayDir = ray.getDirection();

        // Components of Matrix A = [BA CA rayDir]
        double a = vecBA.getCoord(1);
        double b = vecBA.getCoord(2);
        double c = vecBA.getCoord(3);
        double d = vecCA.getCoord(1);
        double e = vecCA.getCoord(2);
        double f = vecCA.getCoord(3);
        double g = rayDir.getCoord(1);
        double h = rayDir.getCoord(2);
        double i = rayDir.getCoord(3);
        double j = vecEA.getCoord(1);
        double k = vecEA.getCoord(2);
        double l = vecEA.getCoord(3);

        // Compute determinant |A| = a(ei - hf) + b(gf - di) + c(dh - eg)
        double det = a * (e * i - h * f) + b * (g * f - d * i) + c * (d * h - e * g);

        // Compute ray parameter t = (f(ak - jb) + e(jc - al) + d(bl - kc)) / |A|
        double t = -(f * (a * k - j * b) + e * (j * c - a * l) + d * (b * l - k * c)) / det;
        // Check if t lies within current interval [t0,t1]
        if (t > t1 || t < t0) {
            return false; // Ray parameter is outside current interval [t0,t1]
        }

        // Compute gamma y = (i(ak - jb) + h(jc - al) + g(bl - kc)) / |A|
        double gamma = (i * (a * k - j * b) + h * (j * c - a * l) + g * (b * l - k * c)) / det;
        // Check if the ray intersects the plane within the triangle
        if (gamma < 0 || gamma > 1) {
            return false; // Ray intersects the plane outside the triangle
        }

        // Compute beta = (j(ei - hf ) + k(gf - di) + l(dh - eg)) / |A|
        double beta = (j * (e * i - h * f) + k * (g * f - d * i) + l * (d * h - e * g)) / det;
        // Check if the ray intersects the plane within the triangle
        if (beta < 0 || beta > 1 - gamma) {
            return false; // Ray intersects the plane outside the triangle
        }
        
        // Update hitrecord with triangle data
        record.setHitObject(this); // Set this triangle as hit object in hitrecord
        record.setT(t); // Set ray parameter of intersection
        record.setHitPoint(ray.pointAt(t)); // Calculate intersection point
        record.setMaterial(material); // Set triangle material
        Vector3D vecHA =  vertices[0].getlocationVector().sub(ray.pointAt(t).getlocationVector());
        Vector3D vecHB =  vertices[1].getlocationVector().sub(ray.pointAt(t).getlocationVector());
        Vector3D normalVec = vecHA.cross(vecHB).normalize();
        Vector3D viewDirection = ray.getDirection();
        record.setViewRayDirection(viewDirection);
        Vertex3D[] verticesAll  = getAllVert();
        record.setAllVert(verticesAll);
        record.setNormalVector(normalVec);
        return true;
    }
}